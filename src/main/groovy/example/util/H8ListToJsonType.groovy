package example.util

import java.sql.Types

class H8ListToJsonType extends H8AbstractJsonType<ArrayList> {

    @Override
    int[] sqlTypes() {
        return [Types.VARCHAR] as int[]
    }
}
