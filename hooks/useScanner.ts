import {useEffect, useId} from 'react'
import {DeviceEventEmitter, NativeModules} from 'react-native'

const {ScannerModule} = NativeModules

type OnCallBackScanner = (data: string) => void

export const useScanner = (
  onCallbackScanner: OnCallBackScanner,
  id?: string,
) => {
  const _id = useId()

  const onScanner = (data: string) => {
    onCallbackScanner && onCallbackScanner(data)
  }

  useEffect(() => {
    const eventId = id ?? _id
    ScannerModule.onInit(eventId)
    DeviceEventEmitter.addListener(`onScanner-${eventId}`, onScanner)
  }, [_id])
}
