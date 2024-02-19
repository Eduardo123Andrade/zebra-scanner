import React, {useState} from 'react'
import {Button, StyleSheet, Text, View} from 'react-native'
import {useScanner} from '../hooks'
import {SimpleModal} from '../modals'

export const SecondScreen = () => {
  const [message, setMessage] = useState<string>()
  const [showModal, setShowModal] = useState(false)

  useScanner(setMessage, {
    id: 'SecondScreen',
    canScan: !showModal,
  })

  const onCloseRequest = () => {
    setShowModal(false)
  }

  const onPress = () => {
    setShowModal(true)
  }

  return (
    <View style={styles.container}>
      <View style={styles.textContainer}>
        <Text>{`resposne: ${message ?? 'No message'}`}</Text>
      </View>
      <Button title="Open modal" onPress={onPress} />
      <SimpleModal visible={showModal} onRequestClose={onCloseRequest} />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#3c3',
    flex: 1,
    justifyContent: 'space-between',
    paddingVertical: 20,
  },
  textContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
})
