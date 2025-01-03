package ordertaking.domain

import cats.data.ValidatedNel
import cats.syntax.all.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.cats.*
import io.github.iltotore.iron.constraint.all.*

object GizmoRefined extends App {
  // Refinement types
  type StartsWithG = DescribedAs[StartWith["G"], "Should start with G"]
  // Note, I had to prefix my regex with `.` to match any character, which I don't hugely like
  type Match3Digits = DescribedAs[Match[".[0-9][0-9][0-9]"], "Should match 3 digits"]
  // Combine refinements
  type GizmoCode = DescribedAs[StartsWithG & Match3Digits, "Should be G followed by three digits"]
  case class Gizmo(code: String :| GizmoCode)

  // CATS
  /** This is just one example of the cats typeclasses Iron supports
    */
  def createGizmoValidatedNel(code: String): ValidatedNel[String, Gizmo] =
    code.refineValidatedNel[GizmoCode].map(t => Gizmo.apply(t))

  println(createGizmoValidatedNel("G123"))  // Valid(Gizmo(G123)
  println(createGizmoValidatedNel("123"))   // Invalid(NonEmptyList(Should be G followed by three digits))
  println(createGizmoValidatedNel("G1234")) // Invalid(NonEmptyList(Should be G followed by three digits))

  // Why doesn't this work as an alternative implementation:
  //  for {
  //    startsWithG <- code.refineValidatedNel[StartsWithG]
  //    validCode   <- startsWithG.refineFurtherValidatedNel[Match3Digits]
  //  } yield Gizmo(validCode)

  // EITHER

  /** Refines the input string step by step. This is an example of composing refined types together.
    */
  def createGizmoEither(code: String): Either[String, Gizmo] =
    for {
      startsWithG <- code.refineEither[StartsWithG]
      validCode   <- startsWithG.refineFurtherEither[Match3Digits]
    } yield Gizmo(validCode)

  println(createGizmoEither("G123"))  // Right(Gizmo(G123))
  println(createGizmoEither("123"))   // Left(Should start with G)
  println(createGizmoEither("G1234")) // Left(Should match 3 digits)

  /** Refining the input string in one go using the `GizmoCode` type
    */
  def createGizmo(code: String): Either[String, Gizmo] =
    code.refineEither[GizmoCode].map(Gizmo.apply)
}
