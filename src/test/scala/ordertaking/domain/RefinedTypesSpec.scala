package ordertaking.domain

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import ordertaking.domain._
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*

class RefinedTypesSpec extends AnyFreeSpec with Matchers {
  "ProductCode" - {
    import ordertaking.domain.ProductCode._
    "Gizmos" - {
      "Correct code" in {
        "val gizmo = Gizmo(\"G123\")" should compile
      }
      "Incorrect code" in {
        "val gizmo = Gizmo(\"G1234\")" shouldNot compile
      }
    }
    "Widgets" - {
      "Correct code" in {
        "val widget = Widget(\"W1234\")" should compile
      }
      "Incorrect code" in {
        "val widget = Widget(\"A12345\")" shouldNot compile
      }
    }
  }
  "OrderQuantity" - {
    import ordertaking.domain.OrderQuantity._
    "Units" - {
      "Correct quantity" in {
        "val quantity = UnitQuantity(20)" should compile
      }
      "Incorrect quantity" in {
        "val quantity = UnitQuantity(-20)" shouldNot compile
      }
    }
    "Kilograms" - {
      "Correct quantity" in {
        "val quantity = KilogramQuantity(0.5f)" should compile
      }
      "Incorrect quantity" in {
        "val quantity = KilogramQuantity(0.01f)" shouldNot compile
      }
    }
  }

}
