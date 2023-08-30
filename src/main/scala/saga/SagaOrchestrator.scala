package saga

import saga.SagaStep.{Completed, CreateOrder, SagaStep, SendMoneyToAccounting, SummonImp}
import saga.service.ApiData.ApiResponse
import saga.service.accounting.AccountingApi
import saga.service.accounting.AccountingApiData.{DepositFundsRequest, FundsDepositedResponse, OrderRefundedResponse, RefundOrderRequest}
import saga.service.reception.ReceptionApi
import saga.service.reception.ReceptionApiData.{CancelOrderRequest, CompleteOrderRequest, CreateOrderRequest, OrderCanceledResponse, OrderCreatedResponse, ReceptionApiData}
import saga.service.summoningcircle.SummoningCircleApiData.{AbortSummoningRequest, StartSummoningRequest, SummoningAbortedResponse, SummoningFailedResponse, SummoningSucceededResponse}
import saga.service.summoningcircle.SummoningCircleApi

import java.util.UUID

// One orchestrator per one saga, each saga is distinguished via the orderId.
class SagaOrchestrator(
                        orderId: UUID,
                        receptionApi: ReceptionApi,
                        accountingApi: AccountingApi,
                        summoningCircleApi: SummoningCircleApi
                      ) {
  private var currentStep: SagaStep = CreateOrder()

  def getCurrentStep(): SagaStep = currentStep

  def advanceStep(): Unit =
    currentStep match
      case CreateOrder() =>
        receptionApi.createOrder(CreateOrderRequest(orderId))
      case SendMoneyToAccounting() =>
        accountingApi.depositFunds(DepositFundsRequest(orderId, 10))
      case SummonImp() =>
        summoningCircleApi.startSummoning(StartSummoningRequest(orderId))
      case Completed() =>
        receptionApi.completeOrder(CompleteOrderRequest(orderId))

  def compensateStep(): Unit =
    currentStep match
      case CreateOrder() =>
        receptionApi.cancelOrder(CancelOrderRequest(orderId))
      case SendMoneyToAccounting() =>
        accountingApi.refundOrder(RefundOrderRequest(orderId))
      case SummonImp() =>
        summoningCircleApi.abortSummoning(AbortSummoningRequest(orderId))
      case Completed() =>
        ()

  def processResponse(response: ApiResponse): Unit =
    currentStep match
      case CreateOrder() =>
        response match
          case OrderCreatedResponse(_) =>
            currentStep = SendMoneyToAccounting()
            advanceStep();
          case OrderCanceledResponse(_) =>
            currentStep = Completed()
          case _ =>
            System.out.println("Received response " + response + " which does not match workflow in step " + currentStep)

      case SendMoneyToAccounting() =>
        response match
          case FundsDepositedResponse(_) =>
            currentStep = SummonImp()
            advanceStep()
          case OrderRefundedResponse(_, _) =>
            currentStep = CreateOrder()
            compensateStep()
          case _ =>
            System.out.println("Received response " + response + " which does not match workflow in step " + currentStep)

      case SummonImp() =>
        response match
          case SummoningSucceededResponse(_) =>
            currentStep = Completed()
            advanceStep()
          case SummoningFailedResponse(_) =>
            currentStep = SendMoneyToAccounting()
            compensateStep()
          case SummoningAbortedResponse(_) =>
            currentStep = SendMoneyToAccounting()
            compensateStep()

      case Completed() =>
        System.out.println("Received response " + response + " which does not match workflow in step " + currentStep)
}
