package ar.edu.itba.rutinas_fit.data

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)