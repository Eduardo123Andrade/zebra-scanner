/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {StyleSheet} from 'react-native';

import {Navigator} from './navigators';

function App(): React.JSX.Element {
  // const scannerResponse = useScanner(console.log);

  return <Navigator />;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default App;
