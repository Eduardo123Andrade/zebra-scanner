import React, {useState} from 'react'
import {StyleSheet, Text, View} from 'react-native'
import {useScanner} from '../hooks'

export const SecondScreen = () => {
  const [message, setMessage] = useState<string>()

  useScanner(data => {
    setMessage(data)
  }, 'SecondScreen')

  return (
    <View style={styles.container}>
      <Text>{`resposne: ${message ?? 'No message'}`}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#3c3',
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
})
