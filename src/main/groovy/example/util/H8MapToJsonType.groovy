package example.util

import java.sql.Types

class H8MapToJsonType extends H8AbstractJsonType<HashMap> {

    @Override
    int[] sqlTypes() {
        return [Types.VARCHAR] as int[]
    }
}
