package saga.service.accounting

import saga.service.accounting.AccountingApiData.{DepositFundsRequest, RefundOrderRequest}

import java.util.UUID

trait AccountingApi:
  def depositFunds(payload: DepositFundsRequest): Unit
  def refundOrder(payload: RefundOrderRequest): Unit
