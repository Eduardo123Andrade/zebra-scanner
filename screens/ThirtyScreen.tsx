import React, {useState} from 'react'
import {StyleSheet, Text, View} from 'react-native'
import {useScanner} from '../hooks'

export const ThirtyScreen = () => {
  const [barcode, setBarcode] = useState<string>()
  useScanner(setBarcode)

  return (
    <View style={styles.container}>
      <Text>{barcode}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
})
