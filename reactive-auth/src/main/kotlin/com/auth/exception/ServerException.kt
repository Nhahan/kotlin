package com.auth.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
): RuntimeException(message) {
    class BadRequestException(message: String): ServerException(400, message)
    class UnauthorizedException(message: String): ServerException(401, message)
    class ForbiddenException(message: String): ServerException(403, message)
    class NotFoundException(message: String): ServerException(404, message)
    class ConflictException(message: String): ServerException(409, message)
    class InternalServerException(message: String): ServerException(500, message)
}