import {MatPaginatorIntl} from '@angular/material/paginator';

export class MatPaginatorIntlPol extends MatPaginatorIntl {
  itemsPerPageLabel = 'Elementów na stronie';
  firstPageLabel = 'Pierwsza strona';
  lastPageLabel = 'Ostatnia strona';
  nextPageLabel = 'Następna strona';
  previousPageLabel = 'Poprzednia strona';

  getRangeLabel = (page, pageSize, length) => {
    if (length === 0 || pageSize === 0) {
      return '0 z ' + length;
    }
    length = Math.max(length, 0);
    const startIndex = page * pageSize;
    const endIndex = startIndex < length ?
      Math.min(startIndex + pageSize, length) :
      startIndex + pageSize;
    return startIndex + 1 + ' - ' + endIndex + ' z ' + length;
  }
}
