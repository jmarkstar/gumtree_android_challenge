package com.jmarkstar.gumtree_challenge.repositories.network.response

// Parse the api error response; `code` is not being considered
// because in some cases it is Int type and other ones is String.
data class ApiErrorResponse(val message: String)
