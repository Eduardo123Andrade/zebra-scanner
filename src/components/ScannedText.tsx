import React, {useEffect, useState} from 'react'
import {StyleSheet, Text, View} from 'react-native'
import {useScanner} from '../hooks'
import {useIsFocused, useNavigation} from '@react-navigation/native'

export const ScannedText = () => {
  const [message, setMessage] = useState<string>()
  useScanner(setMessage, {id: 'ScannerText'})

  const navigation = useNavigation()

  useEffect(
    () =>
      navigation.addListener('blur', () => {
        console.log('bye')
      }),
    [navigation],
  )

  useEffect(
    () =>
      navigation.addListener('focus', () => {
        console.log('Olar')
      }),
    [navigation],
  )

  const text = message ?? 'no scan'
  return (
    <View style={styles.container}>
      <Text>{text}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    borderBottomWidth: 1,
  },
})
