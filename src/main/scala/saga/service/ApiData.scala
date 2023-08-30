package saga.service

case object ApiData:
  trait ApiData
  trait ApiRequest extends ApiData
  trait ApiResponse extends ApiData
