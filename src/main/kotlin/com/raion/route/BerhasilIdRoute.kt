package com.raion.route

import com.raion.model.body.ReviewBody
import com.raion.model.body.UMKMBody
import com.raion.model.body.UserBody
import com.raion.repository.BerhasilIdRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.locations.post
import io.ktor.locations.put

@OptIn(KtorExperimentalLocationsAPI::class)
class BerhasilIdRoute {
    private val repository = BerhasilIdRepository()

    private fun Route.addNewUser(){
        post<RouteLocation.UserPostRoute>{
            val body = try {
                call.receive<UserBody>()
            } catch (e: Exception){
                //call.error(e)
                return@post
            }
            call.response { repository.addNewUser(body) }
        }
    }

    private fun Route.updateUser(){
        put<RouteLocation.UserPutUpdateRoute>{

            val uid = try {
                call.parameters["uid"]
            } catch (e: Exception) {
                //call.error(e)
                return@put
            } ?: ""

            val body = try {
                call.receive<UserBody>()
            } catch (e: Exception) {
                return@put
            }

            call.response { repository.updateUser(uid,body) }
        }
    }

    private fun Route.addNewUMKM(){
        post<RouteLocation.UMKMPostRoute> {
            val body = try {
                call.receive<UMKMBody>()
            } catch (e: Exception){
                //call.error(e)
                return@post
            }
            call.response { repository.addNewUMKM(body) }
        }
    }

    private fun Route.deleteUMKM(){
        delete<RouteLocation.UMKMDeleteRoute>{
            val umkmId = try {
                call.parameters["umkmId"]
            } catch (e: Exception){
                //call.error(e)
                return@delete
            }?: ""
            call.response { repository.deleteUMKM(umkmId) }
        }
    }

    private fun Route.getAllUMKM(){
        get<RouteLocation.UMKMGetAllRoute>{
            val query = try {
                call.request.queryParameters["q"]
            } catch (e: Exception){
                //call.error(e)
                return@get
            }

            if (query!=null)
                call.response { repository.searchMenu(query)}
            else
                call.response { repository.getAllUMKMs() }
        }
    }

    private fun Route.getUMKMDetail(){
        get<RouteLocation.UMKMGetDetailRoute>{
            val umkmId = try {
                call.parameters["umkmId"]
            } catch (e:Exception){
                //call.error(e)
                return@get
            } ?: ""

            call.response { repository.getUMKMDetail(umkmId) }
        }
    }

    private fun Route.addNewReview(){
        post<RouteLocation.UMKMPostReviewRoute>{
            val umkmId = try {
                call.parameters["umkmId"]
            } catch (e: Exception){
                //call.error(e)
                return@post
            } ?: ""

            val body = try {
                call.receive<ReviewBody>()
            } catch (e: Exception){
                //call.error(e)
                return@post
            }

            call.response { repository.addNewReview(umkmId,body) }
        }
    }

    fun Route.initAllRoutes(){
        addNewUser()
        updateUser()
        addNewUMKM()
        deleteUMKM()
        getAllUMKM()
        getUMKMDetail()
        addNewReview()
    }

    suspend inline fun <reified T> ApplicationCall.response(noinline action: suspend () -> T) {
        try {
            this.respond(
                HttpStatusCode.OK,
                action()!!
            )
        } catch (e: Exception){

        }
    }
    suspend inline fun <reified T> ApplicationCall.response(exception: Exception){
        when(exception){
            is BadRequestException -> this.respond(HttpStatusCode.BadRequest, exception)
            is NotFoundException -> this.respond(HttpStatusCode.NotFound, exception)
            else -> this.respond(HttpStatusCode.Conflict, exception)
        }
    }
}