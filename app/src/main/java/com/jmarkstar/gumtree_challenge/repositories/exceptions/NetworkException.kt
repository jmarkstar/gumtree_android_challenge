package com.jmarkstar.gumtree_challenge.repositories.exceptions

class NetworkException(
    val code: Int? = null,
    override val message: String? = null
) : RuntimeException(message)
