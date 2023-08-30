import saga.service.summoningcircle.SummoningCircleApi
import saga.service.summoningcircle.SummoningCircleApiData.{AbortSummoningRequest, StartSummoningRequest}

object SummoningCircleApiMock extends SummoningCircleApi:
    def startSummoning(payload: StartSummoningRequest): Unit = ()
    def abortSummoning(payload: AbortSummoningRequest): Unit = ()
