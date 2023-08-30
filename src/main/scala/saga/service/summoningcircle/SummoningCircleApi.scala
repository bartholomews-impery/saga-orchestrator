package saga.service.summoningcircle

import saga.service.summoningcircle.SummoningCircleApiData.{StartSummoningRequest, AbortSummoningRequest}

import java.util.UUID

trait SummoningCircleApi:
  def startSummoning(payload: StartSummoningRequest): Unit
  def abortSummoning(payload: AbortSummoningRequest): Unit
