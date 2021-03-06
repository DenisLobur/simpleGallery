package simple.gallery.den.simplegallery.screen.presentation.grid;


import java.util.List;

import simple.gallery.den.simplegallery.screen.main.BaseMainView;
import simple.gallery.den.simplegallery.screen.model.Page;

public interface GridView extends BaseMainView {

    void fillGrid(Page page);

    void updateGrid();
}
