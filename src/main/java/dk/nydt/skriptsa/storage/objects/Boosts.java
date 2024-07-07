package dk.nydt.skriptsa.storage.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@DatabaseTable(tableName = "boosts")
public class Boosts {
    @Getter @Setter
    @DatabaseField(generatedId = true, columnName = "id")
    private int id;
    @Getter @Setter
    @DatabaseField(columnName = "uuid")
    private UUID uuid;
    @Getter @Setter
    @DatabaseField(columnName = "username")
    private String username;
    @Getter @Setter
    @DatabaseField(columnName = "since")
    private String since;

    public Boosts() {
    }

    public Boosts(UUID uuid, String username, String since) {
        this.uuid = uuid;
        this.username = username;
        this.since = since;
    }
}
