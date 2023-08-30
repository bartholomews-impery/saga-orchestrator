package saga

object SagaStep:
  sealed trait SagaStep
  case class CreateOrder() extends SagaStep
  case class SendMoneyToAccounting() extends SagaStep
  case class SummonImp() extends SagaStep
  case class Completed() extends SagaStep
