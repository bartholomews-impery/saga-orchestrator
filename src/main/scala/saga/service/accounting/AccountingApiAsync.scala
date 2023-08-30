package saga.service.accounting

import saga.service.accounting.AccountingApi
import saga.service.accounting.AccountingApiData.{DepositFundsRequest, RefundOrderRequest}

import java.util.UUID

object AccountingApiAsync extends AccountingApi:
  def depositFunds(payload: DepositFundsRequest): Unit = ???
  def refundOrder(payload: RefundOrderRequest): Unit = ???
