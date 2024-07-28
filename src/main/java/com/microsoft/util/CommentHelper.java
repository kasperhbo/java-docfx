package com.microsoft.util;

import com.sun.source.doctree.DocTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;

/**
 * The type Comment helper.
 */
public class CommentHelper {
    /**
     * The Element.
     */
    public Element element;
    /**
     * The Inline tags.
     */
    public List<? extends DocTree> inlineTags = Collections.emptyList();
  private Utils utils;
  private boolean hasInheritDocTag = false;

    /**
     * Instantiates a new Comment helper.
     *
     * @param element the element
     * @param utils   the utils
     */
    public CommentHelper(Element element, Utils utils) {
    this.element = element;
    this.utils = utils;
    this.inlineTags = utils.getFullBody(element);
    this.hasInheritDocTag = utils.hasInlineTag(inlineTags, DocTree.Kind.INHERIT_DOC);
  }

    /**
     * Instantiates a new Comment helper.
     *
     * @param element    the element
     * @param utils      the utils
     * @param inlineTags the inline tags
     */
    public CommentHelper(Element element, Utils utils, List<? extends DocTree> inlineTags) {
    this.element = element;
    this.utils = utils;
    this.inlineTags = inlineTags;
    this.hasInheritDocTag = utils.hasInlineTag(inlineTags, DocTree.Kind.INHERIT_DOC);
  }

    /**
     * Returns true if the method has no comments, or a lone &commat;inheritDoc.
     *
     * @return true if there are no comments, false otherwise
     */
    public boolean isSimpleOverride() {
    return inlineTags.isEmpty() || (inlineTags.size() == 1 && hasInheritDocTag);
  }

    /**
     * Has inherit doc tag boolean.
     *
     * @return the boolean
     */
    public boolean hasInheritDocTag() {
    return this.hasInheritDocTag;
  }

    /**
     * Copy comment helper.
     *
     * @return the comment helper
     */
    public CommentHelper copy() {
    if (this.element == null) {
      throw new NullPointerException();
    }
    CommentHelper clone = new CommentHelper(this.element, this.utils);
    return clone;
  }

    /**
     * Inherit comment helper.
     *
     * @param chInheritFrom the ch inherit from
     * @return the comment helper
     */
    public CommentHelper inherit(CommentHelper chInheritFrom) {
    List<? extends DocTree> mergedTags = new ArrayList<>();

    if (this.isSimpleOverride()) mergedTags = chInheritFrom.inlineTags;
    else {
      mergedTags = inheritInlineTags(this, chInheritFrom);
    }

    return new CommentHelper(this.element, this.utils, mergedTags);
  }

    /**
     * Inherit inline tags list.
     *
     * @param origin        the origin
     * @param chInheritFrom the ch inherit from
     * @return the list
     */
    List<? extends DocTree> inheritInlineTags(CommentHelper origin, CommentHelper chInheritFrom) {
    List<DocTree> mergedTags = new ArrayList<>();
    if (!origin.isSimpleOverride() && !origin.hasInheritDocTag) {
      return origin.inlineTags;
    }

    // Get the index of "{@inheritedDoc}".
    int index =
        origin.inlineTags.stream()
            .map(e -> e.getKind())
            .collect(Collectors.toList())
            .indexOf(DocTree.Kind.INHERIT_DOC);

    // Replace the "{@inheritedDoc}" with inherited inlineTags.
    mergedTags = origin.inlineTags.stream().collect(Collectors.toList());
    mergedTags.remove(index);

    for (DocTree d : chInheritFrom.inlineTags) {
      mergedTags.add(index, d);
      index++;
    }

    return mergedTags;
  }
}
