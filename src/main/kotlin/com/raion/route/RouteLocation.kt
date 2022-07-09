@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.raion.route

import io.ktor.locations.*

sealed class RouteLocation {
    companion object {
        // POST
        const val POST_USER = "/user/register"
        // PUT
        const val PUT_UPDATE_USER = "/user/{uid}"
        // POST
        const val POST_UMKM = "/umkm"
        // DELETE
        const val DELETE_UMKM = "/umkm/{umkmId}"
        // GET
        const val GET_ALL_UMKM = "/umkm"
        // GET
        const val GET_UMKM_DETAIL = "/umkm/{umkmId}"
        // POST
        const val POST_REVIEW = "/umkm/{umkmId}/review"
    }

    @Location(POST_USER)
    class UserPostRoute

    @Location(PUT_UPDATE_USER)
    data class UserPutUpdateRoute(val uid: String)

    @Location(POST_UMKM)
    class UMKMPostRoute

    @Location(DELETE_UMKM)
    data class UMKMDeleteRoute(val umkmId: String)

    @Location(GET_ALL_UMKM)
    class UMKMGetAllRoute

    @Location(GET_UMKM_DETAIL)
    data class UMKMGetDetailRoute(val umkmId: String)

    @Location(POST_REVIEW)
    data class UMKMPostReviewRoute(val umkmId: String)
}
