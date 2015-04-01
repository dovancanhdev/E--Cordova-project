package com.truong.brook.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.truong.brook.shared.Book;
import com.truong.brook.shared.Category;
import com.truong.brook.shared.IBasic;

@RemoteServiceRelativePath("dataservice")
public interface DataService extends RemoteService {

	public Category getRootCategory();

	public List<IBasic> getChildrent(Long parentId);

	public Long updateCategory(Category category);

	public Long updateBook(Book book);
}
	