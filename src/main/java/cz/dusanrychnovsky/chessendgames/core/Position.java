package cz.dusanrychnovsky.chessendgames.core;

/**
 * 
 * @author Dušan Rychnovský
 *
 */
public class Position
{
	private final Column column;
	private final Row row;
	
	/**
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	public static Position get(Column column, Row row) {
		return new Position(column, row);
	}
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	public static Position get(String line)
	{
		line = line.trim();
		
		if (line.length() != 2) {
			throw new IllegalArgumentException(
				"Line does not consist of two characters [" + line + "]."
			);
		}
		
		String columnVal = "C" + line.charAt(0);
		Column column = Column.valueOf(columnVal);
		
		String rowVal = "R" + line.charAt(1);
		Row row = Row.valueOf(rowVal);
		
		return new Position(column, row);
	}
	
	/**
	 * 
	 * @param column
	 * @param row
	 */
	private Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}
	
	/**
	 * 
	 * @return
	 */
	public Column getColumn() {
		return column;
	}
	
	/**
	 * 
	 * @return
	 */
	public Row getRow() {
		return row;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean isNeighbour(Position other)
	{
		if (this.equals(other)) {
			return false;
		}
		
		return 
			(column.distance(other.column) <= 1) &&
			(row.distance(other.row) <= 1);
	}
	
	/**
	 * Returns a (correctly ordered) range of positions, starting with the
	 * represented one and ending with the given one.
	 * 
	 * @param other
	 * @return
	 */
	public Range to(Position other) {
		return Range.get(this, other);
	}
	
	@Override
	public int hashCode() {
		return column.hashCode() + row.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Position)) {
			return false;
		}
		
		Position other = (Position) obj;
		return (column.equals(other.column) && row.equals(other.row));
	}
	
	@Override
	public String toString() {
		return "" + column.toString().charAt(1) + row.toString().charAt(1);
	}
}
