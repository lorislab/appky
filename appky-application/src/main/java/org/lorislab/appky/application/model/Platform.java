/*
 * Copyright 2014 lorislab.org.
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


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.lorislab.appky.application.model.enums.PlatformType;
import org.lorislab.jel.jpa.model.TraceablePersistent;

/**
 * The application platform.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Entity
@Table(name = "AY_PLATFORM")
public class Platform extends TraceablePersistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -539633500372041075L;
    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;
    /**
     * The platform type.
     */
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private PlatformType type;
    /**
     * The application.
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_APP_GUID", insertable = false, updatable = false)
    private Application application;
    /**
     * The icon.
     */
    @JoinColumn(name = "C_ICON")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Document icon;
    /**
     * The images.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "AY_PLATFORM_IMAGES",
    joinColumns = {
        @JoinColumn(name = "C_PLATFORM")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_IMAGE")})
    @OrderColumn(name = "C_INDEX")
    private List<Document> images;
    /**
     * The description.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "C_INDEX")
    @JoinTable(name = "AY_PLATFORM_DESC",
    joinColumns = {
        @JoinColumn(name = "C_PLATFORM")},
    inverseJoinColumns = {
        @JoinColumn(name = "C_DESCRIPTION")})
    private List<Description> descriptions;
    /**
     * The iOS manifest.
     */
    @JoinColumn(name = "C_MANIFEST")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ManifestIOS manifest;
    /**
     * The list of versions.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "C_PLATFORM_GUID")
    private List<Version> versions;

    /**
     * The default constructor.
     */
    public Platform() {
        images = new ArrayList<>();
        descriptions = new ArrayList<>();
        versions = new ArrayList<>();
    }

    /**
     * Gets the iOS manifest.
     *
     * @return the iOS manifest.
     */
    public ManifestIOS getManifest() {
        return manifest;
    }

    /**
     * Sets the iOS manifest.
     *
     * @param manifest the iOS manifest.
     */
    public void setManifest(ManifestIOS manifest) {
        this.manifest = manifest;
    }

    /**
     * Gets the platform type.
     *
     * @return the platform type.
     */
    public PlatformType getType() {
        return type;
    }

    /**
     * Sets the platform type.
     *
     * @param type the platform type.
     */
    public void setType(PlatformType type) {
        this.type = type;
    }

    /**
     * Gets the list of description.
     *
     * @return the list of description.
     */
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     * Sets the list of description.
     *
     * @param descriptions the list of description.
     */
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Gets the application.
     *
     * @return the application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application.
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Gets the icon.
     *
     * @return the icon.
     */
    public Document getIcon() {
        return icon;
    }

    /**
     * Sets the icon.
     *
     * @param icon the icon.
     */
    public void setIcon(Document icon) {
        this.icon = icon;
    }

    /**
     * Gets the list of images.
     *
     * @return the list of images.
     */
    public List<Document> getImages() {
        return images;
    }

    /**
     * Sets the list of images.
     *
     * @param images the list of images.
     */
    public void setImages(List<Document> images) {
        this.images = images;
    }

    /**
     * Gets the list of versions.
     *
     * @return the list of versions.
     */
    public List<Version> getVersions() {
        return versions;
    }

    /**
     * Sets the list of versions.
     *
     * @param versions the list of versions.
     */
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    /**
     * Gets the platform enabled flag.
     *
     * @return the platform enabled flag.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the platform enabled flag.
     *
     * @param enabled the platform enabled flag.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
