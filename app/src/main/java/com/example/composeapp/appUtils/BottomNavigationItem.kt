package com.example.composeapp.appUtils

import com.example.composeapp.R

data class BottomNavigationItem(
    val label : String = "",
    val selectedIcon : Int = R.drawable.home_selected,
    val unselectedIcon: Int = R.drawable.home_unselected,
    val route : String = ""
) {
    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                selectedIcon = R.drawable.home_selected,
                unselectedIcon = R.drawable.home_unselected,
                route = BottomNavRoutes.Home.route
            ),
            BottomNavigationItem(
                label = "Explore",
                selectedIcon = R.drawable.explore_selected,
                unselectedIcon = R.drawable.explore_unselected,
                route = BottomNavRoutes.Explore.route
            ),
            BottomNavigationItem(
                label = "",
                selectedIcon = R.drawable.add_icon,
                unselectedIcon = R.drawable.add_icon,
                route = BottomNavRoutes.Upload.route
            ),
            BottomNavigationItem(
                label = "Inbox",
                selectedIcon = R.drawable.inbox_selected,
                unselectedIcon = R.drawable.inbox_unselected,
                route = BottomNavRoutes.Inbox.route
            ),
            BottomNavigationItem(
                label = "Profile",
                selectedIcon = R.drawable.profile_selected,
                unselectedIcon = R.drawable.profile_unselected,
                route = BottomNavRoutes.Profile.route
            ),
        )
    }
}
