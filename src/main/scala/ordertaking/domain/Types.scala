package ordertaking.domain

import io.github.iltotore.iron.*
import ordertaking.domain.Address.{BillingAddress, ShippingAddress}

/** A code to identify a Product the company sells. */
enum ProductCode:
  /** Widgets have a code in the pattern `W1234` */
  case Widget(code: String :| WidgetCode)

  /** Gizmos have a code in the pattern `G123` */
  case Gizmo(code: String :| GizmoCode)

enum OrderQuantity:
  /** An order can have a quantity from 1 to 1000 units */
  case UnitQuantity(q: Int :| RefineUnit)

  /** An order can have a quantity from 0.05kg to 100.0kg */
  case KilogramQuantity(q: Float :| RefineKg)

type OrderId             = String
type OrderLineId         = String
type CustomerId          = String
type Price               = String
type BillingAmount       = String
type OrderPlaced         = Boolean
type BillableOrderPlaced = Boolean

enum Address:
  case ShippingAddress(
      addressLine1: String,
      addressLine2: Option[String],
      city: String,
      postcode: String,
      country: String
  )
  case BillingAddress(
      addressLine1: String,
      addressLine2: Option[String],
      city: String,
      postcode: String,
      country: String
  )

case class Order(
    id: OrderId,
    customerId: CustomerId,
    shippingAddress: ShippingAddress,
    billingAddress: BillingAddress,
    orderLines: List[OrderLine],
    amountToBill: BillingAmount
)

case class OrderLine(
    id: OrderLineId,
    orderId: OrderId,
    productCode: ProductCode,
    orderQuantity: OrderQuantity,
    price: Price
)

case class CustomerInfo(id: CustomerId, billingAddress: BillingAddress)

case class UnvalidatedOrder(orderId: OrderId, customerInfo: CustomerInfo, shippingAddress: ShippingAddress)

case class PlaceOrderEvents(
    acknowledgementSent: Boolean,
    orderPlaced: OrderPlaced,
    billableOrderPlaced: BillableOrderPlaced
)

case class ValidationError(fieldName: String, description: String)

enum PlaceOrderError:
  case ValidationErrors(errors: List[ValidationError])

trait OrderTaking {
  def placeOrder(order: UnvalidatedOrder): Either[PlaceOrderError, PlaceOrderEvents]
}
