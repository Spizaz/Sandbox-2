public class Quadrant implements Comparable<Quadrant>{
    public int x;//the global x position of the quadrant
    public int y;//the global y position of the quadrant
    public Cell[][] cells;//stores the cells that are found in this quadrant
    public static final int QUADRANTSIZE = 10;//the the number of Cells that this quadrant holds in one dimension
    private boolean active;//whether or not this quadrant should be updated
    public boolean filled;//whether this quadrant has been created properly or not

    public Quadrant(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
        cells = new Cell[QUADRANTSIZE][QUADRANTSIZE];
        active = false;
        filled = false;

        fillCells();
    }

    //==================================================================================================================

    //region Gets and Sets


    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        cells[x][y] = cell;
    }

    //sets the element of the cell at (x,y)
    public void setElement(Element element, int x, int y){
        cells[x][y].setElement(element);
    }

    public void setAllElements(Element element){

        //for every Cell in the Quadrant - set the Cell's element to the given type
        for (Cell[] cell : cells) {
            for (Cell value : cell) {

                value.setElement(element.clone());
            }
        }

        filled = true;
    }

    // TODO: 12/9/2019 fix this 
    //checks to see if the cell should be active
    public boolean isActive(){
        return true;
    }


    //endregion

    //==================================================================================================================

    //region Private Methods


    //fills cells with new Cells
    private void fillCells() {

        //for every cell - add a new cell
        for (int x = 0 ; x < 10 ; x++) {
            for (int y = 0 ; y < 10 ; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }

    }


    //endregion

    //==================================================================================================================

    //updates all the cells in cells
    public void update(){
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                value.update();
            }
        }
    }

    //sets the top level of dirt to grass
    public void setTopLevelToGrass(){

        //for every cell - if Dirt is on top - set to Grass
        for (Cell[] cell : cells) {
            for (int y = cell.length - 1 ; y >= 0 ; y--) {

                if (cell[y].getElementType().equals("DIRT")) {
                    cell[y].setElement(new Grass());
                    break;
                }
            }
        }
    }

    //finds the top level of Dirt or Grass and fills in below with Dirt
    public void fillBelowWithDirt(){
        //for every cell - if it is on top
        for (Cell[] cell : cells) {
            boolean foundDirt = false;
            for (int y = cell.length - 1 ; y >= 0 ; y--) {

                if(foundDirt){
                    cell[y].setElement(new Dirt());
                }

                if (cell[y].getElementType().equals("DIRT") || cell[y].getElementType().equals("GRASS")) {
                    foundDirt = true;
                }
            }
        }

        filled = true;
    }

    //==================================================================================================================

    //prints the contents of cells
    public String toString(){
        String end = "";    //the string that is being returned

        //for every cell add the Cell's toString() to end
        for (int y = 0 ; y < cells[x].length ; y++) {
            for (Cell[] cell : cells) {
                end += cell[y] + "\t";
            }

            end += "\n";
        }

        return end;
    }

    @Override
    public int compareTo(Quadrant o) {

        //if the x coordinates are equal - compare y coordinate - else compare x coordinates
        if(this.x - o.x == 0){
            return this.y - o.y;
        }
        else {
            return this.x - o.x;
        }
    }
}
