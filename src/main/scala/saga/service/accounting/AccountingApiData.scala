package saga.service.accounting

import saga.service.ApiData.{ApiRequest, ApiResponse}

import java.util.UUID

object AccountingApiData:
  sealed trait AccountingApiData
  sealed trait AccountingApiRequest extends AccountingApiData, ApiRequest
  sealed trait AccountingApiResponse extends AccountingApiData, ApiResponse
  
  case class DepositFundsRequest(orderId: UUID, amount: Int) extends AccountingApiRequest
  case class FundsDepositedResponse(orderId: UUID) extends AccountingApiResponse
  case class RefundOrderRequest(orderId: UUID) extends AccountingApiRequest
  case class OrderRefundedResponse(orderId: UUID, amount: Int) extends AccountingApiResponse
