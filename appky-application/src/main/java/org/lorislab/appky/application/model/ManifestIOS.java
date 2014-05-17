/*
 * Copyright 2011 Andrej Petras <andrej@ajka-andrej.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.appky.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The iOS manifest.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "ST_MANIFEST_IOS")
public class ManifestIOS extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -539633500372041075L;
    /**
     * The icon shine flag.
     */
    @Column(name = "C_ICONSHINE")
    private boolean iconShine;
    /**
     * The large icon shine flag.
     */
    @Column(name = "C_LARGEICONSHINE")
    private boolean largeIconShine;
    /**
     * The identifier.
     */
    @Column(name = "C_IDENTIFIER")
    private String identifier;
    /**
     * The title.
     */
    @Column(name = "C_TITLE")
    private String title;
    /**
     * The subtitle.
     */
    @Column(name = "C_SUBTITLE")
    private String subtitle;
    /**
     * The icon.
     */
    @JoinColumn(name = "C_SMALLICON")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Document smallIcon;
    /**
     * The largeIcon.
     */
    @JoinColumn(name = "C_LARGEICON")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Document largeIcon;

    /**
     * Gets the identifier.
     *
     * @return the identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier.
     *
     * @param identifier the identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns
     * <code>true</code> if the icon shine flag is true.
     *
     * @return <code>true</code> if the icon shine flag is true.
     */
    public boolean isIconShine() {
        return iconShine;
    }

    /**
     * Sets the icon shine flag.
     *
     * @param iconShine the icon shine flag.
     */
    public void setIconShine(boolean iconShine) {
        this.iconShine = iconShine;
    }

    /**
     * Returns
     * <code>true</code> if the large icon shine flag is true.
     *
     * @return <code>true</code> if the large icon shine flag is true.
     */
    public boolean isLargeIconShine() {
        return largeIconShine;
    }

    /**
     * Sets the large icon shine.
     *
     * @param largeIconShine the large icon shine.
     */
    public void setLargeIconShine(boolean largeIconShine) {
        this.largeIconShine = largeIconShine;
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the subtitle.
     *
     * @return the subtitle.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the subtitle.
     *
     * @param subtitle the subtitle.
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Gets the small icon.
     *
     * @return the small icon.
     */
    public Document getSmallIcon() {
        return smallIcon;
    }

    /**
     * Sets the small icon.
     *
     * @param smallIcon the small icon.
     */
    public void setSmallIcon(Document smallIcon) {
        this.smallIcon = smallIcon;
    }

    /**
     * Gets the large icon.
     *
     * @return the large icon.
     */
    public Document getLargeIcon() {
        return largeIcon;
    }

    /**
     * Sets the large icon.
     *
     * @param largeIcon the large icon.
     */
    public void setLargeIcon(Document largeIcon) {
        this.largeIcon = largeIcon;
    }
}
