package com.nour.core.common.error

import com.nour.core.common.base.BaseError

enum class DatabaseError : BaseError {
    QUERY_FAILED,
    DELETE_FAILED
}