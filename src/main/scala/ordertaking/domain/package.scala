package ordertaking

import io.github.iltotore.iron.*
import io.github.iltotore.iron.cats.*
import io.github.iltotore.iron.constraint.all.*
package object domain {
  type StartsWithG  = DescribedAs[StartWith["G"], "Should start with G"]
  type Match3Digits = DescribedAs[Match[".[0-9][0-9][0-9]"], "Should match 3 digits"]
  type GizmoCode    = DescribedAs[StartsWithG & Match3Digits, "Should be G followed by 3 digits"]
  type StartsWithW  = DescribedAs[StartWith["W"], "Should start with W"]
  type Match4Digits = DescribedAs[Match[".[0-9][0-9][0-9][0-9]"], "Should match 4 digits"]
  type WidgetCode   = DescribedAs[StartsWithW & Match4Digits, "Should be W followed by 4 digits"]
  type MinimumUnit  = DescribedAs[Greater[0], "Should be greater than 0"]
  type MaximumUnit  = DescribedAs[LessEqual[1000], "Should be less than or equal to 1000"]
  type RefineUnit   = DescribedAs[MinimumUnit & MaximumUnit, "Unit should be 0 > Unit >= 1000"]
  type MinimumKg    = DescribedAs[GreaterEqual[0.05], "Should be greater or equal to 0.5kg"]
  type MaximumKg    = DescribedAs[LessEqual[100.0], "Should be less than or equal to 100.0kg"]
  type RefineKg     = DescribedAs[MinimumKg & MaximumKg, "Kg should be 0.05 >= Kg >= 100.0"]
}
