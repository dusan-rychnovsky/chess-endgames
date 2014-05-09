package cz.dusanrychnovsky.chessendgames;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece
{
	@Override
	public List<Position> generateMoves(Position position)
	{
		List<Position> result = new ArrayList<Position>();
		
		List<Position> horizontalMoves = generateHorizontalMoves(position);
		result.addAll(horizontalMoves);
		
		List<Position> verticalMoves = generateVerticalMoves(position);
		result.addAll(verticalMoves);
		
		return result;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	private List<Position> generateVerticalMoves(Position position)
	{
		List<Position> result = new ArrayList<Position>();
		
		for (Row row : Row.values())
		{
			if (!row.equals(position.getRow())) {
				result.add(Position.get(position.getColumn(), row));
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	private List<Position> generateHorizontalMoves(Position position)
	{
		List<Position> result = new ArrayList<Position>();
		
		for (Column column : Column.values())
		{
			if (!column.equals(position.getColumn())) {
				result.add(Position.get(column, position.getRow()));
			}
		}
		
		return result;
	}
}
