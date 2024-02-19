import React, {useState} from 'react'
import {View, StyleSheet, Text, Button} from 'react-native'
import {BaseModalProps} from './BaseModal'
import {Modal} from './Modal'
import {useScanner} from '../hooks'

interface SimpleModalProps extends BaseModalProps {
  title?: string
  message?: string
  label?: string
  onPress?: () => void
}

export const SimpleModal: React.FC<SimpleModalProps> = ({
  title = 'Ops',
  message,
  label = 'OK',
  onPress,
  children,
  ...rest
}) => {
  const [scanner, setScanner] = useState<string>()

  const onReceiver = (data: string) => {
    if (rest.visible) {
      setScanner(data)
    }
  }

  useScanner(onReceiver, {
    id: 'Modal',
    canScan: rest.visible,
  })

  const {onRequestClose} = rest
  const _onPress = () => {
    if (onPress) return onPress()
    onRequestClose()
  }

  return (
    <Modal
      style={{backgroundColor: '#F2f2f2'}}
      contentContainerStyle={[styles.container]}
      {...rest}>
      <View style={styles.titleContainer}>
        <Text>Scanner result</Text>
      </View>

      <View style={styles.messageContainer}>
        <Text>{scanner}</Text>
      </View>

      <View>
        <Button title="Fechar" onPress={_onPress} />
      </View>
    </Modal>
  )
}

const styles = StyleSheet.create({
  container: {
    justifyContent: 'space-evenly',
  },
  titleContainer: {
    alignItems: 'center',
  },
  messageContainer: {
    alignItems: 'center',
    paddingVertical: 12,
  },
})
