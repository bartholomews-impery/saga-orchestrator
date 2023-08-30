package saga.service.summoningcircle

import saga.service.ApiData.{ApiRequest, ApiResponse}

import java.util.UUID

object SummoningCircleApiData:
  sealed trait SummoningCircleData
  sealed trait SummoningCircleRequest extends SummoningCircleData, ApiRequest
  sealed trait SummoningCircleResponse extends SummoningCircleData, ApiResponse
  
  case class StartSummoningRequest(orderId: UUID) extends SummoningCircleRequest
  case class SummoningSucceededResponse(orderId: UUID) extends SummoningCircleResponse
  case class SummoningFailedResponse(orderId: UUID) extends SummoningCircleResponse
  case class AbortSummoningRequest(orderId: UUID) extends SummoningCircleRequest
  case class SummoningAbortedResponse(orderId: UUID) extends SummoningCircleResponse
  case class SummoningAlreadyCompletedResponse(orderId: UUID) extends SummoningCircleResponse
