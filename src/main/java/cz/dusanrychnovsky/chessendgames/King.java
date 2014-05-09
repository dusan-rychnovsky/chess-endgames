package cz.dusanrychnovsky.chessendgames;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	@Override
	public List<Position> generateMoves(Position position)
	{
		List<Position> result = new ArrayList<Position>();
		
		Column column = position.getColumn();
		Row row = position.getRow();
		
		if (!row.isFirst())
		{
			if (!column.isFirst()) {
				result.add(Position.get(column.previous(), row.previous()));
			}
			
			result.add(Position.get(column, row.previous()));
			
			if (!column.isLast()) {
				result.add(Position.get(column.next(), row.previous()));
			}
		}
		
		if (!column.isLast()) {
			result.add(Position.get(column.next(), row));
		}
		
		if (!row.isLast())
		{
			if (!column.isLast()) {
				result.add(Position.get(column.next(), row.next()));
			}

			result.add(Position.get(column, row.next()));
			
			if (!column.isFirst()) {
				result.add(Position.get(column.previous(), row.next()));
			}
		}
		
		if (!column.isFirst()) {
			result.add(Position.get(column.previous(), row));
		}
		
		return result;
	}
}
