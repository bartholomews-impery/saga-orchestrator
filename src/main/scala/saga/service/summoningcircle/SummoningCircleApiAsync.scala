package saga.service.summoningcircle

import saga.service.summoningcircle.SummoningCircleApiData.{AbortSummoningRequest, StartSummoningRequest}
import saga.service.summoningcircle.SummoningCircleApi

import java.util.UUID

object SummoningCircleApiAsync extends SummoningCircleApi:
  def startSummoning(payload: StartSummoningRequest): Unit = ???
  def abortSummoning(payload: AbortSummoningRequest): Unit = ???
