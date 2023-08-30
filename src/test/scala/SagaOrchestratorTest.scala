import org.scalatest.flatspec.AnyFlatSpec
import saga.{SagaOrchestrator, SagaStep}
import saga.SagaStep.{Completed, SendMoneyToAccounting, SummonImp}
import saga.service.accounting.AccountingApiData.FundsDepositedResponse
import saga.service.reception.ReceptionApiData.{OrderCanceledResponse, OrderCreatedResponse}
import saga.service.summoningcircle.SummoningCircleApiData.SummoningSucceededResponse

import java.util.UUID

class SagaOrchestratorTest extends AnyFlatSpec {

  behavior of "Saga Orchestrator"

  it should "Complete the saga on happy path" in {
    val uuid = UUID.randomUUID()

    val sagaOrchestrator = new SagaOrchestrator(
      uuid,
      receptionApi = ReceptionApiMock,
      accountingApi = AccountingApiMock,
      summoningCircleApi = SummoningCircleApiMock
    )

    sagaOrchestrator.advanceStep()
    sagaOrchestrator.processResponse(OrderCreatedResponse(uuid))
    assert(sagaOrchestrator.getCurrentStep() match
      case SendMoneyToAccounting() => true
      case _ => false
    )

    sagaOrchestrator.advanceStep()
    sagaOrchestrator.processResponse(FundsDepositedResponse(uuid))
    assert(sagaOrchestrator.getCurrentStep() match
      case SummonImp() => true
      case _ => false
    )

    sagaOrchestrator.advanceStep()
    sagaOrchestrator.processResponse(SummoningSucceededResponse(uuid))
    assert(sagaOrchestrator.getCurrentStep() match
      case Completed() => true
      case _ => false
    )
  }

  it should "Compensate the create order step" in {
    val uuid = UUID.randomUUID()

    val sagaOrchestrator = new SagaOrchestrator(
      uuid,
      receptionApi = ReceptionApiMock,
      accountingApi = AccountingApiMock,
      summoningCircleApi = SummoningCircleApiMock
    )

    sagaOrchestrator.advanceStep()
    sagaOrchestrator.processResponse(OrderCanceledResponse(uuid))
    assert(sagaOrchestrator.getCurrentStep() match
      case Completed() => true
      case _ => false
    )
  }
}
