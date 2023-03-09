import java.sql.SQLException;

interface RequestDetailsPanel {
    public void setUpdate( int row) throws SQLException;

    public void setNew();
}
