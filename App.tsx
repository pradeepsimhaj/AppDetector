import React, { useEffect, useState } from 'react';
import {
  Text,
  Button,
  StyleSheet,
  NativeModules,
  DeviceEventEmitter,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';


const { AppDetector } = NativeModules;

const App = () => {
  const [lastDetectedApp, setLastDetectedApp] = useState<string>('None');

  useEffect(() => {
    const subscription = DeviceEventEmitter.addListener(
      'APP_CHANGED',
      (packageName: string) => {
        setLastDetectedApp(packageName);
      }
    );

    return () => {
      subscription.remove();
    };
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>App Detector</Text>

      <Button
        title="Enable Accessibility Service"
        onPress={() => AppDetector.openAccessibilitySettings()}
      />

      <Text style={styles.label}>Last detected app:</Text>
      <Text style={styles.value}>{lastDetectedApp}</Text>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 24,
    marginTop: 50,
    
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 24,
    textAlign: 'center',
  },
  label: {
    marginTop: 32,
    fontSize: 16,
    color: '#555',
  },
  value: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '600',
  },
});

export default App;
