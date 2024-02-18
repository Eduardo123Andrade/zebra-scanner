import {NavigationContainer} from '@react-navigation/native'
import React from 'react'
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {HomeScreen, SecondScreen, ThirtyScreen} from '../screens'

const Stack = createNativeStackNavigator()

export const Navigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="HomeScreen" component={HomeScreen} />
        <Stack.Screen name="SecondScreen" component={SecondScreen} />
        <Stack.Screen name="ThirtyScreen" component={ThirtyScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  )
}
