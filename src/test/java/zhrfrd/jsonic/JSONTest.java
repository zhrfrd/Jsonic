package zhrfrd.jsonic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {
    @Test
    void testComplexJson() {
        String json = """
        {
            "name": "Bubba",
            "scores": [10, 20, 30],
            "meta": {
                "country": "France",
                "active": true
            }
        }
        """;

        JSONObject obj = JSON.parse(json).asObject();

        assertEquals("Bubba", obj.getString("name"));

        JSONArray scores = obj.getArray("scores");
        assertEquals(3, scores.size());
        assertEquals(20, scores.get(1).asNumber());

        JSONObject meta = obj.getObject("meta");
        assertEquals("France", meta.getString("country"));
        assertEquals(true, meta.getBoolean("active"));
    }
}