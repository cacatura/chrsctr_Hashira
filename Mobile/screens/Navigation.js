import React from "react";
import { StyleSheet, Text, View } from "react-native";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import LoginScreen from "./LoginScreen";
import HomeScreen from "./HomeScreen";
import { Box } from "@mui/material";

const Tab = createBottomTabNavigator();

const Navigation = () => {
  return (
    <View style={styles.nav}>
      <Box sx={{ width: 500 }}>
        <BottomNavigation
          showLabels
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
        >
          <BottomNavigationAction label="Recents" icon={<RestoreIcon />} />
          <BottomNavigationAction label="Favorites" icon={<FavoriteIcon />} />
          <BottomNavigationAction label="Nearby" icon={<LocationOnIcon />} />
        </BottomNavigation>
      </Box>
    </View>
  );
};

const styles = StyleSheet.create({
  nav: {
    justifyContent: "center",
    alignItems: "center",
    flex: 1,
  },
});

export default Navigation;
