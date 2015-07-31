package bl;

import java.util.Optional;

public class Tile {
	Optional<Integer> value;
	
	public Tile() {
		value = Optional.of(0);
	}
	
	public Tile(Optional<Integer> num) {
		value = num;
	}
	
	public String getValue() {
		return value.get().toString();
	}
	
	public boolean isEmpty() {
		return value.get() == 0;
	}
	
	public String getForeground() {
		return value.get() < 16 ? "#776e65" :  "#f9f6f2";
	}
	
	public String getBackground() {
		switch (value.get()) { 
			case 2:    return "#eee4da"; 
			case 4:    return "#ede0c8"; 
			case 8:    return "#f2b179"; 
			case 16:   return "#f59563"; 
			case 32:   return "#f67c5f"; 
			case 64:   return "#f65e3b"; 
			case 128:  return "#edcf72"; 
			case 256:  return "#edcc61"; 
			case 512:  return "#edc850"; 
			case 1024: return "#edc53f"; 
			case 2048: return "#edc22e"; 
		} 
		return "#cdc0b4"; 
	}
}
