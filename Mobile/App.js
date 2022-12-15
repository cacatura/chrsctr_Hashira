import * as React from "react";
import { DrawerActions, NavigationContainer } from "@react-navigation/native";
import AuthNavigator from "./src/navigation/AuthNavigator";
import "react-native-gesture-handler";
import LoginScreen from "./src/screens/auth/LoginScreen";

export default function App() {
  return (
    <NavigationContainer>
      <AuthNavigator />
    </NavigationContainer>
  );
}
