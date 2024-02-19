import {useEffect, useId} from 'react'
import {DeviceEventEmitter, NativeModules} from 'react-native'

const {ScannerModule} = NativeModules

type OnCallBackScanner = (data: string) => void
interface UseScannerConfig {
  id?: string
  canScan?: boolean
}

const DEFAULT_CONFIG: UseScannerConfig = {
  canScan: true,
}

export const useScanner = (
  onCallbackScanner: OnCallBackScanner,
  config?: UseScannerConfig,
) => {
  const _id = useId()
  const _config = {...DEFAULT_CONFIG, ...config}

  const onScanner = (data: string) => {
    onCallbackScanner(data)
  }

  useEffect(() => {
    if (!_config.canScan) return

    const eventId = _config.id ?? _id
    ScannerModule.onInit(eventId)
    DeviceEventEmitter.addListener(`onScanner-${eventId}`, onScanner)
  }, [_id, _config])
}
