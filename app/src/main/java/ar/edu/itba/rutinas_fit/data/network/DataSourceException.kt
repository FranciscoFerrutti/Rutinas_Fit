package ar.edu.itba.rutinas_fit.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)