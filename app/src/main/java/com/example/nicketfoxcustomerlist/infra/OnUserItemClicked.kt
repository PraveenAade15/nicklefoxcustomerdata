package com.example.nicketfoxcustomerlist.infra

import com.example.nicketfoxcustomerlist.model.User

interface OnUserItemClicked {
    fun onClick(user: User)
}