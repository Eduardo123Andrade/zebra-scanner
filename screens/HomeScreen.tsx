import {useNavigation} from '@react-navigation/native'
import React from 'react'
import {Button, StyleSheet, View} from 'react-native'
import {ScannedText} from '../components'

interface HomeScreenProps {}

export const HomeScreen: React.FC<HomeScreenProps> = () => {
  const navigation = useNavigation<any>()

  const goToSecondScreen = () => {
    navigation.navigate('SecondScreen')
  }

  const goToThirtyScreen = () => {
    navigation.navigate('ThirtyScreen')
  }

  return (
    <View style={styles.container}>
      <ScannedText />
      <ScannedText />
      <View style={styles.buttonsContainer}>
        <Button title="Go to 2" onPress={goToSecondScreen} />
        <Button title="Go to 3" onPress={goToThirtyScreen} />
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },

  buttonsContainer: {
    gap: 18,
    paddingBottom: 8,
  },
})
