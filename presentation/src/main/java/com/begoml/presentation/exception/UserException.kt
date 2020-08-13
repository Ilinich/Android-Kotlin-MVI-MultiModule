package com.begoml.presentation.exception

import java.lang.Exception

class NotImplementedException : Exception()

class InitComponentException: RuntimeException("You must call 'init' method")