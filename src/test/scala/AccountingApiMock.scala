import saga.service.accounting.AccountingApi
import saga.service.accounting.AccountingApiData.{DepositFundsRequest, RefundOrderRequest}

object AccountingApiMock extends AccountingApi:
  def depositFunds(payload: DepositFundsRequest): Unit = ()
  def refundOrder(payload: RefundOrderRequest): Unit = ()
